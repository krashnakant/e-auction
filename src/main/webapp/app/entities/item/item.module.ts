import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';
import { EauctionItemAttributeModule } from '../item-attribute/item-attribute.module';
import { EauctionBidModule } from '../bid/bid.module';

import {
    ItemService,
    ItemPopupService,
    ItemComponent,
    ItemCardComponent,
    ItemDetailComponent,
    ItemLabelComponent,
    ItemDialogComponent,
    ItemPopupComponent,
    ItemDeletePopupComponent,
    ItemDeleteDialogComponent,
    itemRoute,
    itemPopupRoute,
} from './';

const ENTITY_STATES = [
    ...itemRoute,
    ...itemPopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        EauctionItemAttributeModule,
        EauctionBidModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ItemComponent,
        ItemCardComponent,
        ItemDetailComponent,
        ItemLabelComponent,
        ItemDialogComponent,
        ItemDeleteDialogComponent,
        ItemPopupComponent,
        ItemDeletePopupComponent,
    ],
    entryComponents: [
        ItemComponent,
        ItemCardComponent,
        ItemLabelComponent,
        ItemDialogComponent,
        ItemPopupComponent,
        ItemDeleteDialogComponent,
        ItemDeletePopupComponent,
    ],
    providers: [
        ItemService,
        ItemPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [
        ItemCardComponent,
        ItemLabelComponent
    ]
})
export class EauctionItemModule {}
