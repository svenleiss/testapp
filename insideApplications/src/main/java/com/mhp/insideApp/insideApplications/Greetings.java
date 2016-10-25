package com.mhp.insideApp.insideApplications;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Greetings {

    @Wither
    private Long id;

    @Wither
    private String userName;

    @Wither
    private String message;

    @Override
    public Greetings clone() throws CloneNotSupportedException {
        super.clone();

        return this.withId(this.getId());
    }
}
