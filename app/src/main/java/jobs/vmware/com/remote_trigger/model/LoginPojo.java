package jobs.vmware.com.remote_trigger.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class    LoginPojo {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("repository_name")
    @Expose
    private String repository_name;
    @SerializedName("domain_name")
    @Expose
    private String domain_name;

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    @SerializedName("repository")
    @Expose
    private String repository;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepository_name() {
        return repository_name;
    }

    public void setRepository_name(String repository_name) {
        this.repository_name = repository_name;
    }

    public String getDomain_name() {
        return domain_name;
    }

    public void setDomain_name(String domain_name) {
        this.domain_name = domain_name;
    }





}

