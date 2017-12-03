import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';
import { EauctionAttributeModule } from '../attribute/attribute.module';

import {
    ItemAttributeService,
    ItemAttributePopupService,
    ItemAttributeComponent,
    ItemAttributeListComponent,
    ItemAttributeLabelComponent,
    ItemAttributeDetailComponent,
    ItemAttributeDialogComponent,
    ItemAttributePopupComponent,
    ItemAttributeDeletePopupComponent,
    ItemAttributeDeleteDialogComponent,
    itemAttributeRoute,
    itemAttributePopupRoute,
} from './';

const ENTITY_STATES = [
    ...itemAttributeRoute,
    ...itemAttributePopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        EauctionAttributeModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ItemAttributeComponent,
        ItemAttributeListComponent,
        ItemAttributeLabelComponent,
        ItemAttributeDetailComponent,
        ItemAttributeDialogComponent,
        ItemAttributeDeleteDialogComponent,
        ItemAttributePopupComponent,
        ItemAttributeDeletePopupComponent,
    ],
    entryComponents: [
        ItemAttributeComponent,
        ItemAttributeListComponent,
        ItemAttributeLabelComponent,
        ItemAttributeDialogComponent,
        ItemAttributePopupComponent,
        ItemAttributeDeleteDialogComponent,
        ItemAttributeDeletePopupComponent,
    ],
    providers: [
        ItemAttributeService,
        ItemAttributePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [
        ItemAttributeListComponent,
        ItemAttributeLabelComponent
    ]
})
export class EauctionItemAttributeModule {}
