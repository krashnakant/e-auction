import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';
import { EauctionAdminModule } from '../../admin/admin.module';
import {
    UserAccountService,
    UserAccountPopupService,
    UserAccountComponent,
    UserAccountDetailComponent,
    UserAccountDialogComponent,
    UserAccountPopupComponent,
    UserAccountDeletePopupComponent,
    UserAccountDeleteDialogComponent,
    userAccountRoute,
    userAccountPopupRoute,
} from './';

const ENTITY_STATES = [
    ...userAccountRoute,
    ...userAccountPopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        EauctionAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UserAccountComponent,
        UserAccountDetailComponent,
        UserAccountDialogComponent,
        UserAccountDeleteDialogComponent,
        UserAccountPopupComponent,
        UserAccountDeletePopupComponent,
    ],
    entryComponents: [
        UserAccountComponent,
        UserAccountDialogComponent,
        UserAccountPopupComponent,
        UserAccountDeleteDialogComponent,
        UserAccountDeletePopupComponent,
    ],
    providers: [
        UserAccountService,
        UserAccountPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EauctionUserAccountModule {}
