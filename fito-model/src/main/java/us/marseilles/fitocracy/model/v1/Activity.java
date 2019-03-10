package us.marseilles.fitocracy.model.v1;

import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * API v1
 * <p>
 * Describes a Fitocracy exercise
 */
@Getter
@Setter
public class Activity extends FitoV1Entity
{
    @Key("actiontype")
    private short actionType;

    @Key
    private String description;

    @Key
    private float multiplier;

    @Key
    private String name;

    @Key("set_name")
    private String setName;

    @Key
    private Boolean effort0;

    @Key("effort0_label")
    private String effort0Label;

    @Key
    private Boolean effort1;

    @Key("effort1_label")
    private String effort1Label;

    @Key
    private Boolean effort2;

    @Key("effort2_label")
    private String effort2Label;

    @Key
    private Boolean effort3;

    @Key("effort3_label")
    private String effort3Label;

    @Key
    private Boolean effort4;

    @Key("effort4_label")
    private String effort4Label;

    @Key
    private Boolean effort5;

    @Key("effort5_label")
    private String effort5Label;
}