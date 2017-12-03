import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EauctionSharedModule } from '../../shared';
import {
    SubCategoryService,
    SubCategoryPopupService,
    SubCategoryComponent,
    SubCategoryLabelComponent,
    SubCategoryDetailComponent,
    SubCategoryDialogComponent,
    SubCategoryPopupComponent,
    SubCategoryDeletePopupComponent,
    SubCategoryDeleteDialogComponent,
    subCategoryRoute,
    subCategoryPopupRoute,
} from './';
import { EauctionCategoryModule } from '../category/category.module';

const ENTITY_STATES = [
    ...subCategoryRoute,
    ...subCategoryPopupRoute,
];

@NgModule({
    imports: [
        EauctionSharedModule,
        EauctionCategoryModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        SubCategoryComponent,
        SubCategoryLabelComponent,
        SubCategoryDetailComponent,
        SubCategoryDialogComponent,
        SubCategoryDeleteDialogComponent,
        SubCategoryPopupComponent,
        SubCategoryDeletePopupComponent,
    ],
    entryComponents: [
        SubCategoryComponent,
        SubCategoryLabelComponent,
        SubCategoryDialogComponent,
        SubCategoryPopupComponent,
        SubCategoryDeleteDialogComponent,
        SubCategoryDeletePopupComponent,
    ],
    providers: [
        SubCategoryService,
        SubCategoryPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    exports: [
        SubCategoryLabelComponent
    ]
})
export class EauctionSubCategoryModule {}
