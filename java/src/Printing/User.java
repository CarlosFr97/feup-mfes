package Printing;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class User {
  protected String name = SeqUtil.toStr(SeqUtil.seq());
  protected String password = SeqUtil.toStr(SeqUtil.seq());
  protected String birthDay = SeqUtil.toStr(SeqUtil.seq());

  protected void initUser(final String username, final String pass) {

    String atomicTmp_4 = pass;
    String atomicTmp_5 = username;
    {
        /* Start of atomic statement */
      password = atomicTmp_4;
      name = atomicTmp_5;
    } /* End of atomic statement */
  }

  public String getName() {

    return name;
  }

  public Malfunction reportMalfunction(
      final Printer printer, final Object type, final String desc) {

    Malfunction malf = new Malfunction(this, printer, ((Object) type), desc);
    PrintManager.getInstance().addMalfunction(malf);
    return malf;
  }

  public Boolean isLoginCorrected(final String username, final String pass) {

    Boolean andResult_77 = false;

    if (Utils.equals(name, username)) {
      if (Utils.equals(password, pass)) {
        andResult_77 = true;
      }
    }

    if (andResult_77) {
      return true;
    }

    return false;
  }

  public User() {}

  public String toString() {

    return "User{"
        + "name := "
        + Utils.toString(name)
        + ", password := "
        + Utils.toString(password)
        + ", birthDay := "
        + Utils.toString(birthDay)
        + "}";
  }
}
