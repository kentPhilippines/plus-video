package org.dromara.common.core.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author jerry
 */
@Data
@Accessors(chain = true)
public class MyFieldDTO {
    public boolean enable  ;
    public boolean required ;
}
