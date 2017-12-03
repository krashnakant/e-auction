import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';
import {
    BidService,
    BidPopupService,
    BidNowComponent,
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
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BidComponent,
        BidNowComponent,
        BidDetailComponent,
        BidDialogComponent,
        BidDeleteDialogComponent,
        BidPopupComponent,
        BidDeletePopupComponent,
    ],
    entryComponents: [
        BidComponent,
        BidNowComponent,
        BidDialogComponent,
        BidPopupComponent,
        BidDeleteDialogComponent,
        BidDeletePopupComponent,
    ],
    providers: [
        BidService,
        BidPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [
        BidNowComponent
    ]
})
export class EauctionBidModule {}
