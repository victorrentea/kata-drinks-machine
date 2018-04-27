package musicbox;

@SuppressWarnings("serial")
public class GetraenkException extends RuntimeException {
	
	public enum ErrorCode {
		INCORRECT_PROVIDED_PARAMETER,
		KEIN_KAPAZITAET_MEHR,
		KEIN_WECHSELGELD,
		NICHT_GEFUNDEN_AUSWAHL,
		NICHT_GENUG_GELD,
		NICHT_GUELTIG_GETRAENK
	}
	
	private ErrorCode errorCode;
	
	public GetraenkException(ErrorCode errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode() {
		return errorCode;
	}

}
