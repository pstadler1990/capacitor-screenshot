import { WebPlugin } from '@capacitor/core';
import type { ScreenshotPlugin } from './definitions';
export declare class ScreenshotWeb extends WebPlugin implements ScreenshotPlugin {
    take(): Promise<{
        base64: string;
    }>;
}
