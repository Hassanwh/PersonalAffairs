package code.person.pojo.message;

public class ScanCodePushEvent extends InMessage{

	private String ScanCodeInfo;
	private String ScanResult;
	private String ScanType;

	public ScanCodePushEvent() {
		super();
	}

	public String getScanCodeInfo() {
		return ScanCodeInfo;
	}

	public void setScanCodeInfo(String scanCodeInfo) {
		ScanCodeInfo = scanCodeInfo;
	}

	public String getScanResult() {
		return ScanResult;
	}

	public void setScanResult(String scanResult) {
		ScanResult = scanResult;
	}

	public String getScanType() {
		return ScanType;
	}

	public void setScanType(String scanType) {
		ScanType = scanType;
	}
	
	
	
}
