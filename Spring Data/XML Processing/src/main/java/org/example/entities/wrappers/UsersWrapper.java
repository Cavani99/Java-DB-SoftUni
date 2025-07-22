package org.example.entities.wrappers;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.example.entities.Products;
import org.example.entities.Users;

import java.util.List;

@XmlRootElement(name = "users")
public class UsersWrapper {

    private List<Users> users;

    @XmlElement(name = "user")
    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public UsersWrapper() {
    }

    public UsersWrapper(List<Users> users) {
        this.users = users;
    }
}