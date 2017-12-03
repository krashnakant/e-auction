import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';
import {
    AttributeService,
    AttributePopupService,
    AttributeComponent,
    AttributeLabelComponent,
    AttributeDetailComponent,
    AttributeDialogComponent,
    AttributePopupComponent,
    AttributeDeletePopupComponent,
    AttributeDeleteDialogComponent,
    attributeRoute,
    attributePopupRoute,
} from './';
import { EauctionSubCategoryModule } from '../sub-category/sub-category.module';

const ENTITY_STATES = [
    ...attributeRoute,
    ...attributePopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        EauctionSubCategoryModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        AttributeComponent,
        AttributeLabelComponent,
        AttributeDetailComponent,
        AttributeDialogComponent,
        AttributeDeleteDialogComponent,
        AttributePopupComponent,
        AttributeDeletePopupComponent,
    ],
    entryComponents: [
        AttributeComponent,
        AttributeLabelComponent,
        AttributeDialogComponent,
        AttributePopupComponent,
        AttributeDeleteDialogComponent,
        AttributeDeletePopupComponent,
    ],
    providers: [
        AttributeService,
        AttributePopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [
        AttributeLabelComponent
    ]
})
export class EauctionAttributeModule {}
