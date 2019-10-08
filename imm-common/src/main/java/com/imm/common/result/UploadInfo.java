package com.imm.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * @author rjyx_huxinsheng
 */
@Data
public class UploadInfo implements Serializable {
    private String path;
    private String thumbPath;

}
