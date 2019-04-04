package code.person.pojo.system;

import java.io.Serializable;
//个人信息实体类
public class PersonInf
  implements Serializable
{
  private String cmgId;
  private String currentDate;
  private String jgbm;
  private String jgmc;
  private String jsId;
  private String jsName;
  private String lastUrl;
  private int maxFocusAcct;
  private String name;
  private String orgClass;
  private String ownerId;
  private String startDate;
  private String userId;
  private String zhBankId;
  private String roleId;


private String lastStrutsPath;
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  

  public String getCmgId()
  {
    return this.cmgId;
  }

  public String getCurrentDate() {
    return this.currentDate;
  }

  public String getJgbm()
  {
    return this.jgbm;
  }

  public String getJgmc()
  {
    return this.jgmc;
  }

  public String getJsId()
  {
    return this.jsId;
  }

  public String getJsName()
  {
    return this.jsName;
  }

  public String getLastUrl()
  {
    return this.lastUrl;
  }

  public int getMaxFocusAcct()
  {
    return this.maxFocusAcct;
  }

  public String getName()
  {
    return this.name;
  }

  public String getOrgClass()
  {
    return this.orgClass;
  }

  public String getOwnerId()
  {
    return this.ownerId;
  }

  public String getStartDate()
  {
    return this.startDate;
  }

  public String getUserId()
  {
    return this.userId;
  }

  public String getZhBankId()
  {
    return this.zhBankId;
  }

  public void setCmgId(String cmgId)
  {
    this.cmgId = cmgId;
  }

  public void setCurrentDate(String currentDate) {
    this.currentDate = currentDate;
  }

  public void setJgbm(String jgbm)
  {
    this.jgbm = jgbm;
  }

  public void setJgmc(String jgmc)
  {
    this.jgmc = jgmc;
  }

  public void setJsId(String jsId)
  {
    this.jsId = jsId;
  }

  public void setJsName(String jsName)
  {
    this.jsName = jsName;
  }

  public void setLastUrl(String lastUrl)
  {
    this.lastUrl = lastUrl;
  }

  public void setMaxFocusAcct(int maxFocusAcct)
  {
    this.maxFocusAcct = maxFocusAcct;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public void setOrgClass(String orgClass)
  {
    this.orgClass = orgClass;
  }

  public void setOwnerId(String ownerId)
  {
    this.ownerId = ownerId;
  }

  public void setStartDate(String startDate)
  {
    this.startDate = startDate;
  }

  public void setUserId(String userId)
  {
    this.userId = userId;
  }

  public void setZhBankId(String zhBankId)
  {
    this.zhBankId = zhBankId;
  }

public void setRoleId(String roleId) {
	this.roleId = roleId;
}

public String getRoleId() {
	return roleId;
}

public String getLastStrutsPath() {
	return lastStrutsPath;
}

public void setLastStrutsPath(String lastStrutsPath) {
	this.lastStrutsPath = lastStrutsPath;
}
}