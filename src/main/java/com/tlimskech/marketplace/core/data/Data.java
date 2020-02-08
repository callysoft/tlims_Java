/**
 *
 */
package com.tlimskech.marketplace.core.data;

import javax.xml.bind.JAXB;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import java.io.*;

/**
 * @author g-smart
 */
public class Data implements Serializable {

    private Paging paging;

    @Override
    public String toString() {
        return toJsonString(false);
    }

    public String toXmlString() {
        StringBuffer buf = new StringBuffer();
        StringWriter os = new StringWriter();
        JAXB.marshal(this, os);
        buf.append(os.toString());
        return buf.toString();
    }

    public String toJsonString(boolean header) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String res = "";
            if (header) {
                res = res + "@" + this.getClass().getName();
            }
            res = res + mapper.writeValueAsString(this);
            return res;
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    /**
     * @return
     * @throws IOException
     */
    public String serialize() throws IOException {
        ByteArrayOutputStream w = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(w);
        out.writeObject(this);
        return w.toString();
    }

    /**
     * copies the properties of the source into this object
     *
     * @param source           to copy from
     * @param ignoreProperties properties to ignore
     */
    public void copy(Object source, String... ignoreProperties) {
        BeanUtils.copyProperties(source, this, ignoreProperties);
    }

    /**
     * copies the properties of the source into this object ignoring properties
     * in BaseEntity :
     * createDate,version,modifiedDate,id,active,createdBy,modifiedBy
     *
     * @param source
     * @param ignoreProperties additional properties you want to ignore
     */
    public void copyForUpdate(Object source, String... ignoreProperties) {
        String ignoreList = "createDate,version,modifiedDate,id,code,active,createdBy,modifiedBy,LastModifiedDate,code,status";
        String[] ignores = StringUtils.concatenateStringArrays(ignoreProperties, ignoreList.split(","));
        BeanUtils.copyProperties(source, this, ignores);
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

}
