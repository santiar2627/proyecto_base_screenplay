package co.com.client.project.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersResponse {

    private Integer page;
    @JsonProperty("per_page") private Integer perPage;
    private Integer total;
    @JsonProperty("total_pages") private Integer totalPages;
    private List<User> data;

    public Integer getPage() { return page; }
    public Integer getPerPage() { return perPage; }
    public Integer getTotal() { return total; }
    public Integer getTotalPages() { return totalPages; }
    public List<User> getData() { return data; }

    // setters opcionales si los necesitas
}

@JsonIgnoreProperties(ignoreUnknown = true)
class User {
    private Integer id;
    private String email;
    @JsonProperty("first_name") private String firstName;
    @JsonProperty("last_name")  private String lastName;
    private String avatar;

    public Integer getId() { return id; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAvatar() { return avatar; }
}