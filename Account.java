package individual1;

abstract class Account {

    private String status;
    private String name;
    private String lastName;
    private String idMember;
    private String email;
    private String passwordHash;

    void changePassword() {
        System.out.print("Enter your previous password: ");

    }

    void changeName() {
        System.out.print("Enter your new name: ");
    }

    void changeLastName() {
        System.out.print("Enter your new lastname: ");

    }

    String getStatus() {
        return status;
    }

    void setStatus(String status) {
        this.status = status;
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
