import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';

import {
    BidService,
    BidPopupService,
    BidComponent,
    BidDetailComponent,
    BidDialogComponent,
    BidPopupComponent,
    BidDeletePopupComponent,
    BidDeleteDialogComponent,
    BidNowComponent,
    bidRoute,
    bidPopupRoute,
} from './';
import { EntitySharedModule } from '../entity-shared.module';

const ENTITY_STATES = [
    ...bidRoute,
    ...bidPopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        EntitySharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BidComponent,
        BidDetailComponent,
        BidDialogComponent,
        BidDeleteDialogComponent,
        BidPopupComponent,
        BidDeletePopupComponent,
        BidNowComponent
    ],
    entryComponents: [
        BidComponent,
        BidDialogComponent,
        BidPopupComponent,
        BidDeleteDialogComponent,
        BidDeletePopupComponent,
        BidNowComponent
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
