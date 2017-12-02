import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { DatePipe } from '@angular/common';

import {
    EauctionSharedLibsModule,
    EauctionSharedCommonModule,
    CSRFService,
    AuthServerProvider,
    AccountService,
    UserService,
    StateStorageService,
    LoginService,
    LoginModalService,
    AppLoginModalComponent,
    Principal,
    AppTrackerService,
    HasAnyAuthorityDirective,
    AppSocialComponent,
    SocialService,
} from './';

@NgModule({
    imports: [
        EauctionSharedLibsModule,
        EauctionSharedCommonModule
    ],
    declarations: [
        AppSocialComponent,
        AppLoginModalComponent,
        HasAnyAuthorityDirective
    ],
    providers: [
        LoginService,
        LoginModalService,
        AccountService,
        StateStorageService,
        Principal,
        CSRFService,
        AppTrackerService,
        AuthServerProvider,
        SocialService,
        UserService,
        DatePipe
    ],
    entryComponents: [AppLoginModalComponent],
    exports: [
        EauctionSharedCommonModule,
        AppSocialComponent,
        AppLoginModalComponent,
        HasAnyAuthorityDirective,
        DatePipe
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]

})
export class EauctionSharedModule {}
