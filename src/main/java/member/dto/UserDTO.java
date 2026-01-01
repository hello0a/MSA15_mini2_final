package member.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
	private int no;
	private String id;
	private String password;
	private String email;
	private String full_name;
	private Date birth;
	private String gender;
	private String nationality;
	private String phonenumber;
	private Timestamp created_at;
	private Timestamp updated_at;
	
}

