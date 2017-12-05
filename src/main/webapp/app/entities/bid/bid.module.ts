import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';
import { EauctionAdminModule } from '../../admin/admin.module';

import {
    BidService,
    BidPopupService,
    BidComponent,
    BidDetailComponent,
    BidDialogComponent,
    BidPopupComponent,
    BidDeletePopupComponent,
    BidDeleteDialogComponent,
    bidRoute,
    bidPopupRoute,
} from './';

const ENTITY_STATES = [
    ...bidRoute,
    ...bidPopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        EauctionAdminModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BidComponent,
        BidDetailComponent,
        BidDialogComponent,
        BidDeleteDialogComponent,
        BidPopupComponent,
        BidDeletePopupComponent,
    ],
    entryComponents: [
        BidComponent,
        BidDialogComponent,
        BidPopupComponent,
        BidDeleteDialogComponent,
        BidDeletePopupComponent,
    ],
    providers: [
        BidService,
        BidPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class EauctionBidModule {}
