package cn.com.kxcomm.contractmanage.vo;

public class ContractDocVariableVo {
    
	private String id;
	
	private String variableName;
	
	private String content;
	
	private String contractDocId;  //合同文件ID
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContractDocId() {
		return contractDocId;
	}

	public void setContractDocId(String contractDocId) {
		this.contractDocId = contractDocId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ContractDocVariableVo [id=" + id + ", variableName="
				+ variableName + ", content=" + content + ", contractDocId="
				+ contractDocId + "]";
	}
	
	
}
