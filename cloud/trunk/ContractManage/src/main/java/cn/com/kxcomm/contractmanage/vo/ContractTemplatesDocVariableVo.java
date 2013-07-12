package cn.com.kxcomm.contractmanage.vo;

public class ContractTemplatesDocVariableVo {
    private String id;
    
    private String description;
    
    private String variableName;
    
    private String templatesDocId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getTemplatesDocId() {
		return templatesDocId;
	}

	public void setTemplatesDocId(String templatesDocId) {
		this.templatesDocId = templatesDocId;
	}

	@Override
	public String toString() {
		return "ContractTemplatesDocVariableVo [id=" + id + ", description="
				+ description + ", variableName=" + variableName
				+ ", templatesDocId=" + templatesDocId + "]";
	}
    
    
}
