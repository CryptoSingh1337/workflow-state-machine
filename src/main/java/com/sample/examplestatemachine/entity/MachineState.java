package com.sample.examplestatemachine.entity;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.Map;

/**
 * @author Saransh Kumar
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "state_machine")
public class MachineState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "machine_id")
    private String machineId;
    @Column(name = "state")
    private String state;
    @Column(name = "class")
    private String clazz;
    @Type(JsonType.class)
    @Column(name = "context", columnDefinition = "json")
    private Map<String, Object> context;
}
