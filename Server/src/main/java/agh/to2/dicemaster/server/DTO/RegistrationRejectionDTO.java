package agh.to2.dicemaster.server.DTO;

public class RegistrationRejectionDTO {
    private String username;

    public RegistrationRejectionDTO() {  // Converter default constructor
    }

    public RegistrationRejectionDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
