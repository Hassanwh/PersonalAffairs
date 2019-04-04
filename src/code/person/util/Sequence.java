package code.person.util;

import org.safehaus.uuid.UUIDGenerator;



/**
 * The system sequence utility.
 */
//序列化id号的类
public class Sequence {
	

	/**
	 * Get the base Sequence.
	 * 
	 * @return the Sequence
	 * @throws SequenceException
	 */
	public static synchronized String getSequence(){
		UUIDGenerator gen = UUIDGenerator.getInstance();
		org.safehaus.uuid.UUID uuid = gen.generateTimeBasedUUID();

		String uuidStr = uuid.toString();
		String[] uuidParts = uuidStr.split("-");
		StringBuffer builder = new StringBuffer();
		builder.append(uuidParts[2]);
		//builder.append("-");
		builder.append(uuidParts[1]);
		//builder.append("-");
		builder.append(uuidParts[3]);
		//builder.append("-");
		builder.append(uuidParts[4]);
		//builder.append("-");
		builder.append(uuidParts[0]);

		return builder.toString();
	}


	public static void main(String[] args) throws Exception {

		// String receiver = "13725303059";
		// String applicationid = "01b807a0-8c7f-3f80-aa27-c741f5c90259";
		// for (int i = 0; i < 260 + 1; i++) {
		// System.out.println(getShortMessageCode(receiver, applicationid));
		// }
		for(int i=0;i<100;i++)
			System.out.println(Sequence.getSequence());// 125292303662500000

	}
}
