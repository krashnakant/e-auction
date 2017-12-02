import { NgModule, LOCALE_ID } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { WindowRef } from './tracker/window.service';
import {
    EauctionSharedLibsModule,
    JhiLanguageHelper,
    FindLanguageFromKeyPipe,
    AppAlertComponent,
    AppAlertErrorComponent
} from './';

@NgModule({
    imports: [
        EauctionSharedLibsModule
    ],
    declarations: [
        FindLanguageFromKeyPipe,
        AppAlertComponent,
        AppAlertErrorComponent
    ],
    providers: [
        JhiLanguageHelper,
        WindowRef,
        Title,
        {
            provide: LOCALE_ID,
            useValue: 'en'
        },
    ],
    exports: [
        EauctionSharedLibsModule,
        FindLanguageFromKeyPipe,
        AppAlertComponent,
        AppAlertErrorComponent
    ]
})
export class EauctionSharedCommonModule {}
