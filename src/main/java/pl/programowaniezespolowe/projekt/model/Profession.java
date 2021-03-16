//package pl.programowaniezespolowe.projekt.model;
//
//import javax.persistence.*;
//
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//public class Profession {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String code;
//
//    private String name;
//
//    @OneToOne
//    private Profession parentId; // id kod.length - 1
//
//    private String synthesis;
//
//    private String tasks;
//
//    private String additionalTasks;
//
//    private String url;
//
//}
