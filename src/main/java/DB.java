import org.sql2o.*;

public class DB {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "moringa", "moringa");
}

////ec2-54-197-254-117.compute-1.amazonaws.com

//import org.sql2o.*;
//
//public class DB {
//    public static Sql2o sql2o = new Sql2o
//            ("jdbc:postgresql://ec2-54-197-254-117.compute-1.amazonaws.com:5432/d63b0h8r54dun6", "praozypfmzqbip", "a1a6cc7330539f2b8072f66aae610112f92e62581c21767829e7b3e63c933e30");
//}