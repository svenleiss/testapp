package com.mhp.insideApp.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer implements Cloneable {

    @Wither
    private Long id;
    @Wither
    private String firstName;
    @Wither
    private String lastName;
    @Wither
    private String email;
    @Wither
    private String telephone;

    @Override
    public Customer clone() throws CloneNotSupportedException {
        super.clone();

        return this.withId(this.getId());
    }
}
