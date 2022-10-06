export interface ScreenshotPlugin {
    take(options: TakeOptions): Promise<{
        base64: string;
    }>;
}
export interface TakeOptions {
    /**
     * Compression quality level (0..100) (worst..best)
     */
    quality?: number;
    /**
     * Image format (JPEG, PNG or WEBP)
     */
    format?: 'JPEG' | 'PNG' | 'WEBP';
}
