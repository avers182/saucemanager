package ru.sav.saucemanager;


import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.sav.saucemanager.domain.Identity;
import ru.sav.saucemanager.domain.QtiResultSet;
import ru.sav.saucemanager.domain.RepositoryEntry;
import ru.sav.saucemanager.repository.IdentityRepository;
import ru.sav.saucemanager.repository.QtiResultSetRepository;
import ru.sav.saucemanager.repository.RepositoryEntryRepository;
import ru.sav.saucemanager.repository.UserPropertyRepository;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class AppTest {
    static Logger log = Logger.getLogger(AppTest.class);


    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    QtiResultSetRepository qtiResultSetRepository;

    @Autowired
    RepositoryEntryRepository repositoryEntryRepository;

    @Autowired
    UserPropertyRepository userPropertyRepository;

    @Autowired
    WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void smoke() {

    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/identity_repository.sql"})
    public void identityRepositoryFindRepositoryEntryParticipantsTest() {
        List<Identity> identities = identityRepository.findRepositoryEntryParticipants(2l);
        assertNotNull(identities);
        assertEquals(2, identities.size());
        assertThat(identities, containsInAnyOrder(hasProperty("name", is("Mr. Smith")), hasProperty("name", is("Mrs. Smith"))));
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/qti_resultset_repository.sql"})
    public void qtiResultSetRepositoryFindByRepositoryEntryIdTest() {
        List<QtiResultSet> qtiResultSets = qtiResultSetRepository.findByRepositoryEntryId(3l);
        assertNotNull(qtiResultSets);
        assertEquals(2, qtiResultSets.size());
        assertThat(qtiResultSets, containsInAnyOrder(hasProperty("id", is(4l)), hasProperty("id", is(5l))));
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/repository_entry_repository_1.sql"})
    public void repositoryEntryRepositoryFindAllCoursesTest() {
        List<RepositoryEntry> repositoryEntries = repositoryEntryRepository.findAllCourses();
        assertNotNull(repositoryEntries);
        assertEquals(2, repositoryEntries.size());
        assertThat(repositoryEntries, containsInAnyOrder(
                hasProperty("displayName", is("Course A")), hasProperty("displayName", is("Course B"))));
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/repository_entry_repository_2.sql"})
    public void repositoryEntryRepositoryFindReferencesTest() {
        List<RepositoryEntry> repositoryEntries = repositoryEntryRepository.findReferences(1l);
        assertNotNull(repositoryEntries);
        assertEquals(1, repositoryEntries.size());
        assertEquals("Test A", repositoryEntries.get(0).getDisplayName());
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/user_property_repository.sql"})
    public void userPropertyRepositoryFindByRepositoryEntryIdTest() {
        List<Object[]> list = userPropertyRepository.findByRepositoryEntryId(1l);
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("John Doe", list.get(0)[1]);
        assertEquals("John", list.get(0)[2]);
        assertEquals("Doe", list.get(0)[3]);
        assertEquals("johndoe@email.com", list.get(0)[4]);
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/repository_entry_repository_2.sql"})
    public void repositoryEntryControllerViewTestsTest() throws Exception {
        log.info(mockMvc.perform(get("/repositoryentry/1/tests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].displayName").value("Test A"))
                .andReturn().getResponse().getContentAsString());
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/user_property_repository.sql"})
    public void repositoryEntryControllerViewUserPropertiesTest() throws Exception {
        log.info(mockMvc.perform(get("/repositoryentry/1/userproperties"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[?('John Doe' in $.[0])]").exists())
                .andExpect(jsonPath("[?('John' in $.[0])]").exists())
                .andExpect(jsonPath("[?('Doe' in $.[0])]").exists())
                .andExpect(jsonPath("[?('johndoe@email.com' in $.[0])]").exists())
                .andReturn().getResponse().getContentAsString());
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/qti_resultset_repository.sql"})
    public void repositoryEntryControllerViewQtiResultSetsTest() throws Exception {
        log.info(mockMvc.perform(get("/repositoryentry/3/qtiresultsets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("[?('4' in $.[*].id)]").exists())
                .andExpect(jsonPath("[?('5' in $.[*].id)]").exists())
                .andExpect(jsonPath("[?(true in $.[*].isPassed)]").exists())
                .andExpect(jsonPath("[?(false in $.[*].isPassed)]").exists())
                .andExpect(jsonPath("[?('2.0' in $.[*].score)]").exists())
                .andExpect(jsonPath("[?('5.0' in $.[*].score)]").exists())
                .andReturn().getResponse().getContentAsString());
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/repository_entry_repository_1.sql"})
    @SuppressWarnings("unchecked")
    public void repositoryEntryControllerListCoursesTest() throws Exception {
        List<RepositoryEntry> repositoryEntries = (List<RepositoryEntry>) mockMvc.perform(get("/repositoryentry/listcourses"))
                .andExpect(status().isOk())
                .andReturn().getModelAndView().getModel().get("repositoryEntries");

        assertThat(repositoryEntries, containsInAnyOrder(
                hasProperty("displayName", is("Course A")), hasProperty("displayName", is("Course B"))));
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/repository_entry_repository_1.sql"})
    @SuppressWarnings("unchecked")
    public void repositoryEntryControllerViewTest() throws Exception {
        RepositoryEntry repositoryEntry = (RepositoryEntry) mockMvc.perform(get("/repositoryentry/view/1"))
                .andExpect(status().isOk())
                .andReturn().getModelAndView().getModel().get("repositoryEntry");

        assertEquals("Course A", repositoryEntry.getDisplayName());
    }
}
