/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnbp.type;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class TypeDTO implements Serializable{
    private String id, typeName;

    public TypeDTO() {
    }

    public TypeDTO(String id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the typeName
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    
    
}
