package reservation.dto;

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
public class DesignerDTO {
	private int no;
	private String id;
	private String password;
	private String email;
	private String full_name;
	private Date birth;
	private String gender;
	private String nationality;
	private String phonenumber;
	private String shop_name;
	private String biz_num;
	private String city;
	private String district;
	private String addr_detail;
	private Timestamp created_at;
	private Timestamp updated_at;
}
