package us.marseilles.fitocracy.rest.domain;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;
import lombok.Getter;
import lombok.Setter;

/**
 * Describes a single set of an exercise
 */
@Getter
@Setter
public class ActivitySet extends GenericJson
{
    @Key
    private long id;

    @Key("action")
    private Activity activity;

    @Key("is_pr")
    private boolean isPr;

    @Key
    private String notes;

    @Key
    private int points;

    @Key
    private String string;
    
    @Key("string_imperial")
    private String stringImperial;
    
    @Key("string_metric")
    private String stringMetric;
    
    @Key
    private int subgroup;
    
    @Key("subgroup_order")
    private int subgroupOrder;
    
    @Key
    private boolean submitted;
    
    @Key
    private User user;

    @Key("action_group_id")
    private long actionGroupId;
    
    @Key("actiondate")
    private DateTime actionDate;
    
    @Key("actiontime")
    private DateTime actionTime;
    
    @Key("allow_share")
    private boolean allowShare;

    @Key("api_id")
    private String apiId;
    
    @Key("api_source")
    private String apiSource;
    
    @Key
    private Double effort0;
    
    @Key("effort0_imperial")
    private Double effort0Imperial;
    
    @Key("effort0_imperial_string")
    private String effort0ImperialString;
    
    @Key("effort0_imperial_unit")
    private Unit effort0ImperialUnit;

    @Key("effort0_metric")
    private Double effort0Metric;

    @Key("effort0_metric_string")
    private String effort0MetricString;

    @Key("effort0_metric_unit")
    private Unit effort0MetricUnit;
    
    @Key("effort0_string")
    private String effort0String;
    
    @Key("effort0_unit")
    private Unit effort0Unit;

    @Key("effort1_imperial")
    private Double effort1Imperial;

    @Key("effort1_imperial_string")
    private String effort1ImperialString;

    @Key("effort1_imperial_unit")
    private Unit effort1ImperialUnit;

    @Key("effort1_metric")
    private Double effort1Metric;

    @Key("effort1_metric_string")
    private String effort1MetricString;

    @Key("effort1_metric_unit")
    private Unit effort1MetricUnit;

    @Key("effort1_string")
    private String effort1String;

    @Key("effort1_unit")
    private Unit effort1Unit;

    @Key("effort2_imperial")
    private Double effort2Imperial;

    @Key("effort2_imperial_string")
    private String effort2ImperialString;

    @Key("effort2_imperial_unit")
    private Unit effort2ImperialUnit;

    @Key("effort2_metric")
    private Double effort2Metric;

    @Key("effort2_metric_string")
    private String effort2MetricString;

    @Key("effort2_metric_unit")
    private Unit effort2MetricUnit;

    @Key("effort2_string")
    private String effort2String;

    @Key("effort2_unit")
    private Unit effort2Unit;

    @Key("effort3_imperial")
    private Double effort3Imperial;

    @Key("effort3_imperial_string")
    private String effort3ImperialString;

    @Key("effort3_imperial_unit")
    private Unit effort3ImperialUnit;

    @Key("effort3_metric")
    private Double effort3Metric;

    @Key("effort3_metric_string")
    private String effort3MetricString;

    @Key("effort3_metric_unit")
    private Unit effort3MetricUnit;

    @Key("effort3_string")
    private String effort3String;

    @Key("effort3_unit")
    private Unit effort3Unit;

    @Key("effort4_imperial")
    private Double effort4Imperial;

    @Key("effort4_imperial_string")
    private String effort4ImperialString;

    @Key("effort4_imperial_unit")
    private Unit effort4ImperialUnit;

    @Key("effort4_metric")
    private Double effort4Metric;

    @Key("effort4_metric_string")
    private String effort4MetricString;

    @Key("effort4_metric_unit")
    private Unit effort4MetricUnit;

    @Key("effort4_string")
    private String effort4String;

    @Key("effort4_unit")
    private Unit effort4Unit;

    @Key("effort5_imperial")
    private Double effort5Imperial;

    @Key("effort5_imperial_string")
    private String effort5ImperialString;

    @Key("effort5_imperial_unit")
    private Unit effort5ImperialUnit;

    @Key("effort5_metric")
    private Double effort5Metric;

    @Key("effort5_metric_string")
    private String effort5MetricString;

    @Key("effort5_metric_unit")
    private Unit effort5MetricUnit;

    @Key("effort5_string")
    private String effort5String;

    @Key("effort5_unit")
    private Unit effort5Unit;
}