package ru.sav.saucemanager;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
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
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebAppConfiguration
public class AppTest {
    @Autowired
    IdentityRepository identityRepository;

    @Autowired
    QtiResultSetRepository qtiResultSetRepository;

    @Autowired
    RepositoryEntryRepository repositoryEntryRepository;

    @Autowired
    UserPropertyRepository userPropertyRepository;

    @Test
    public void smoke() {

    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/identity_repository.sql"})
    public void IdentityRepositoryFindRepositoryEntryParticipantsTest() {
        List<Identity> identities = identityRepository.findRepositoryEntryParticipants(2l);
        assertNotNull(identities);
        assertEquals(2, identities.size());
        assertThat(identities, containsInAnyOrder(hasProperty("name", is("Mr. Smith")), hasProperty("name", is("Mrs. Smith"))));
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/qti_resultset_repository.sql"})
    public void QtiResultSetRepositoryFindByRepositoryEntryIdTest() {
        List<QtiResultSet> qtiResultSets = qtiResultSetRepository.findByRepositoryEntryId(3l);
        assertNotNull(qtiResultSets);
        assertEquals(2, qtiResultSets.size());
        assertThat(qtiResultSets, containsInAnyOrder(hasProperty("id", is(4l)), hasProperty("id", is(5l))));
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/repository_entry_repository_1.sql"})
    public void RepositoryEntryRepositoryFindAllCoursesTest() {
        List<RepositoryEntry> repositoryEntries = repositoryEntryRepository.findAllCourses();
        assertNotNull(repositoryEntries);
        assertEquals(2, repositoryEntries.size());
        assertThat(repositoryEntries, containsInAnyOrder(
                hasProperty("displayName", is("Course A")), hasProperty("displayName", is("Course B"))));
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/repository_entry_repository_2.sql"})
    public void RepositoryEntryRepositoryFindReferencesTest() {
        List<RepositoryEntry> repositoryEntries = repositoryEntryRepository.findReferences(1l);
        assertNotNull(repositoryEntries);
        assertEquals(1, repositoryEntries.size());
        assertEquals("Test A", repositoryEntries.get(0).getDisplayName());
    }

    @Test
    @Sql({"/sql/cleanup.sql", "/sql/user_property_repository.sql"})
    public void UserPropertyRepositoryFindByRepositoryEntryIdTest() {
        List<Object[]> list = userPropertyRepository.findByRepositoryEntryId(1l);
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("John Doe", list.get(0)[1]);
        assertEquals("John", list.get(0)[2]);
        assertEquals("Doe", list.get(0)[3]);
        assertEquals("johndoe@email.com", list.get(0)[4]);
    }
}
