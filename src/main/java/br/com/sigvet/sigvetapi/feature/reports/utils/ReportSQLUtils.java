package br.com.sigvet.sigvetapi.feature.reports.utils;

public final class ReportSQLUtils {
    
    public static final String GENERAL_METRICS_SQL = """
        SELECT 
            (SELECT COUNT(*) FROM clients c, users u WHERE c.id = u.id AND u.deleted IS FALSE) as "totalClients",
            (SELECT COUNT(*) FROM animals a WHERE a.deleted IS FALSE) as "totalAnimals",
            (SELECT COUNT(*) FROM consults c WHERE c.deleted IS FALSE) as "totalConsults",
            (SELECT COUNT(*) FROM consults c WHERE c.deleted IS FALSE AND status = 'COMPLETED') * 350 as "revenue";
        """;

    public static final String GENERAL_METRICS_RESULT_MAPPING_KEY = "GeneralMetricsResult";

    public static final String MONTHLY_ANIMAL_CREATION_COUNT_SQL =  """
        SELECT COUNT(*) AS "count", EXTRACT(MONTH FROM a.created_at) AS "month"
            FROM animals a
            WHERE EXTRACT(YEAR FROM a.created_at) = EXTRACT(YEAR FROM CURRENT_DATE) AND a.deleted IS FALSE
            GROUP BY "month";       
    """;

    public static final String MONTHLY_ANIMAL_CREATION_COUNT_RESULT_MAPPING_KEY = "MonthlyAnimalCreationCount";


    public static final String MONTHLY_CLIENT_CREATION_COUNT_SQL = """
        SELECT COUNT(*) as "count", EXTRACT(MONTH FROM u.created_at) AS "month"
        FROM CLIENTS c
        INNER JOIN users u ON u.id = c.id
        WHERE EXTRACT(YEAR FROM u.created_at) = EXTRACT(YEAR FROM CURRENT_DATE) AND u.deleted IS FALSE
        GROUP BY "month";
    """;

    public static final String MONTHLY_CLIENT_CREATION_COUNT_RESULT_MAPPING_KEY = "MonthlyClientCreationCount";

    public static final String TOTAL_COUNT_VACCINE_OF_USES_SQL = """
        SELECT COUNT(*) as "count"
        FROM vaccines v1
        INNER JOIN vaccinations v2 ON v1.id = v2.vaccine_id
        WHERE 
            v1.deleted IS FALSE
            AND v2.deleted IS FALSE
            AND v1.id = ?1
    """;

    public static final String TOTAL_COUNT_VACCINE_OF_USES_RESULT_MAPPING_KEY = "TotalCountVaccineOfUses";

}
