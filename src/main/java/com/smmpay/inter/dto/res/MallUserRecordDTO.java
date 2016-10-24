package com.smmpay.inter.dto.res;

public class MallUserRecordDTO {
    private Integer id;

    private String email;

    private String mallAccount;

    private String code;

    private String createTime;
    
    private String realName;
    
    private String mallUserName;
    
    private String date;
    
    

    public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMallUserName() {
		return mallUserName;
	}

	public void setMallUserName(String mallUserName) {
		this.mallUserName = mallUserName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMallAccount() {
        return mallAccount;
    }

    public void setMallAccount(String mallAccount) {
        this.mallAccount = mallAccount == null ? null : mallAccount.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }
}