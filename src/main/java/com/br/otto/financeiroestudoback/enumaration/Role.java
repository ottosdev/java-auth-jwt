package com.br.otto.financeiroestudoback.enumaration;

public enum Role {
    ADMIN,
    USER,
    FINANCEIRO;

    public String getRoleName() {
        return "ROLE_" + this.name();
    }
}
