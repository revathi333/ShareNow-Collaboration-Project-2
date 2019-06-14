
public class test
{
 static int v=10;
 int j=9;
 test u=this;
  void runn()
 {
	 System.out.println(this.j);
	 System.out.println(this);
	 System.out.println(hashCode());
	 System.out.println(this.hashCode());
	 System.out.println(u.j);
		System.out.println("Check "+Integer.toHexString(hashCode())+"eq"+hashCode());


 }	 
	 static void runnn()
	 {
		 System.out.println(v);
	 }
	 
	 public static void main(String[] args)
	 {
 		 test o =new test();
 		 o.runn();
 		 System.out.println("tostring method "+o);

 		 String s=o.toString();
		 System.out.println(o.hashCode());test o2 =o;
		 o2.runn();
		 System.out.println(o2.hashCode()); 		 System.out.println("tostring method "+o2);

	 }
}
