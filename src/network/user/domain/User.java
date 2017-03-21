package network.user.domain;

public class User {
private String name;
private String grade;
private  String describle;
private String password;
public synchronized String getName() {
	return name;
}
public synchronized void setName(String name) {
	this.name = name;
}
public synchronized String getGrade() {
	return grade;
}
public synchronized void setGrade(String grade) {
	this.grade = grade;
}
public synchronized String getDescrible() {
	return describle;
}
public synchronized void setDescrible(String describle) {
	this.describle = describle;
}
@Override
public String toString() {
	return "User [name=" + name + ", grade=" + grade + ", describle="
			+ describle + "]";
}
public synchronized String getPassword() {
	return password;
}
public synchronized void setPassword(String password) {
	this.password = password;
}
	

}
