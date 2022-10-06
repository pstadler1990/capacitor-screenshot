'use strict';

Object.defineProperty(exports, '__esModule', { value: true });

var core = require('@capacitor/core');

const Screenshot = core.registerPlugin('Screenshot', {
    web: () => Promise.resolve().then(function () { return web; }).then(m => new m.ScreenshotWeb()),
});

class ScreenshotWeb extends core.WebPlugin {
    async take() {
        throw new Error('Method not implemented.');
    }
}

var web = /*#__PURE__*/Object.freeze({
    __proto__: null,
    ScreenshotWeb: ScreenshotWeb
});

exports.Screenshot = Screenshot;
//# sourceMappingURL=plugin.cjs.js.map
