package individual1;

abstract class Account {

    private String name;
    private String lastName;
    private String idMember;
    private String email;
    private String passwordHash;

    void changePassword() {

    }

    void changeName() {

    }

    void changeLastName() {

    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getLastName() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName = lastName;
    }

    String getEmail() {
        return email;
    }

    void setEmail(String email) {
        this.email = email;
    }

    String getPassword() {
        return passwordHash;
    }

    void setPassword(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    String getIdMember() {
        return idMember;
    }

    void setIdMember(String idMember) {
        this.idMember = idMember;
    }
}
