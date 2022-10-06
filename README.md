<p align="center"><br><img src="https://user-images.githubusercontent.com/236501/85893648-1c92e880-b7a8-11ea-926d-95355b8175c7.png" width="128" height="128" /></p>
<h3 align="center">Screenshot</h3>
<p align="center"><strong><code>capacitor-screenshot</code></strong></p>
<p align="center">
  Capacitor community plugin for take screenshot.
</p>

## Updated by DFKI

## Maintainers

| Maintainer             | GitHub                                | Social                                  |
| ---------------------- | ------------------------------------- | --------------------------------------- |
| Luan Freitas (ludufre) | [ludufre](https://github.com/ludufre) | [@ludufre](https://twitter.com/ludufre) |
 | Patrick Stadler       | [pstadler1990](https://github.com/pstadler1990) | - |

## Installation

`npm install capacitor-screenshot`

## Configuration

Not needed.

## Usage

```typescript
import { Screenshot } from 'capacitor-screenshot';

...

Screenshot.take().then((ret: { base64: string }) => {
    console.log(ret.base64); // or `data:image/png;base64,${ret.base64}`
});
```
