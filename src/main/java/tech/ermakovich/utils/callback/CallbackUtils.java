package tech.ermakovich.utils.callback;

import lombok.experimental.UtilityClass;

import static tech.ermakovich.utils.callback.CallbackKeys.TOGGLE_SOURCE;

@UtilityClass
public class CallbackUtils {
    public String generateCallbackQueryDataKey(String data) {
        if (data.startsWith(TOGGLE_SOURCE)) {
            return TOGGLE_SOURCE;
        }
        return data;
    }
}
