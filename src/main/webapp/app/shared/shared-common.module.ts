import { NgModule, LOCALE_ID } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { WindowRef } from './tracker/window.service';
import {
    EauctionSharedLibsModule,
    JhiLanguageHelper,
    FindLanguageFromKeyPipe,
    UserLabelComponent,
    AppAlertComponent,
    AppAlertErrorComponent
} from './';

@NgModule({
    imports: [
        EauctionSharedLibsModule
    ],
    declarations: [
        FindLanguageFromKeyPipe,
        UserLabelComponent,
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
        UserLabelComponent,
        AppAlertComponent,
        AppAlertErrorComponent
    ]
})
export class EauctionSharedCommonModule {}
