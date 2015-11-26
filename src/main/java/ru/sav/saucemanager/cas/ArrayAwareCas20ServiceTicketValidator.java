package ru.sav.saucemanager.cas;

import org.jasig.cas.client.util.XmlUtils;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

public class ArrayAwareCas20ServiceTicketValidator extends Cas20ServiceTicketValidator {

    public ArrayAwareCas20ServiceTicketValidator(String casServerUrlPrefix) {
        super(casServerUrlPrefix);
    }
    
    /**
     * Default attribute parsing of attributes that look like the following:
     * &lt;cas:attributes&gt;
     *  &lt;cas:attribute1&gt;value&lt;/cas:attribute1&gt;
     *  &lt;cas:attribute2&gt;value&lt;/cas:attribute2&gt;
     * &lt;/cas:attributes&gt;
     * <p>
     * This code is here merely for sample/demonstration purposes for those wishing to modify the CAS2 protocol.  You'll
     * probably want a more robust implementation or to use SAML 1.1
     *
     * @param xml the XML to parse.
     * @return the map of attributes.
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected Map extractCustomAttributes(final String xml) {
        final int pos1 = xml.indexOf("<cas:attributes>");
        final int pos2 = xml.indexOf("</cas:attributes>");
        
        if (pos1 == -1) {
            return Collections.EMPTY_MAP;
        }
        
        final String attributesText = xml.substring(pos1+16, pos2);
        
        final Map attributes = new HashMap();
        final BufferedReader br = new BufferedReader(new StringReader(attributesText));
        
        String line;
        final List attributeNames = new ArrayList();
        try {
            while ((line = br.readLine()) != null) {
                final String trimmedLine = line.trim();
                if (trimmedLine.length() > 0) {
                    final int leftPos = trimmedLine.indexOf(":");
                    final int rightPos = trimmedLine.indexOf(">");
                    attributeNames.add(trimmedLine.substring(leftPos+1, rightPos));
                }
            }
            br.close();
        } catch (final IOException e) {
            //ignore
        }
        
        for (final Iterator iter = attributeNames.iterator(); iter.hasNext();) {
            final String name = (String) iter.next();
            String value = XmlUtils.getTextForElement(xml, name);
            value = value.trim();
            if (value.startsWith("[") && value.endsWith("]")) {
                String[] values = value.substring(1, value.length()-1).split(",");
                List<String> valueList = new ArrayList<String>(values.length);
                for (String val: values) {
                    log.debug("Attr "+name+" item "+val);
                    valueList.add(val.trim());
                }
                log.debug("Attr "+name+" = "+valueList);
                attributes.put(name, valueList);
            } else {
                attributes.put(name, value);
            }
            
        }
        
        return attributes;
    }


}
