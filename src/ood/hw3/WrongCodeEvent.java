package ood.hw3;


import java.util.List;




public  class WrongCodeEvent {
	
	public enum errorType {

		UNKNOWN("Warning! unkonwn"), ADDING_ITEM("Warning! someone just tried to add an item with wrong code"), TAKING_ITEM(
				"Warning! someone just tried to take an item with wrong code"), CHANGE_CODE("WARNING!! someone failed to change the safe code");

		private String value;

		errorType(String value) {
			this.value = value;
		}

		public String getMessege() {
			return value;
		}

	}

	public WrongCodeEvent() {
		super();
		

	}

	private errorType eType;
	

	public void seteType(errorType eType) {
		this.eType = eType;
	}

	public errorType getErrorType() {
		return eType;
	}
	
	public String getDescription() {
		return eType.getMessege();
	}

}
