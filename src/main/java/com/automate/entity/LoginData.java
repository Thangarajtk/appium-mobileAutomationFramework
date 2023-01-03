package com.automate.entity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class LoginData {

  private String loginUsername;
  private String loginPassword;
}
