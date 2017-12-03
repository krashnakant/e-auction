import { NgModule } from '@angular/core';
import { AttributeLabelComponent } from './attribute/index';
import { CategoryLabelComponent } from './category/index';
import { ItemLabelComponent } from './item/index';
import { ItemAttributeLabelComponent } from './item-attribute/index';
import { SaleLabelComponent } from './sale/index';
import { SubCategoryLabelComponent } from './sub-category/index';
import { UserLabelComponent, UserAccountLabelComponent } from './user-account/index';

import { EauctionSharedModule } from '../shared';

@NgModule({
    imports: [
        EauctionSharedModule
    ],
    declarations: [
        AttributeLabelComponent,
        CategoryLabelComponent,
        ItemLabelComponent,
        ItemAttributeLabelComponent,
        SaleLabelComponent,
        SubCategoryLabelComponent,
        UserLabelComponent,
        UserAccountLabelComponent
    ],
    entryComponents: [
        AttributeLabelComponent,
        CategoryLabelComponent,
        ItemLabelComponent,
        ItemAttributeLabelComponent,
        SaleLabelComponent,
        SubCategoryLabelComponent,
        UserLabelComponent,
        UserAccountLabelComponent
    ],
    exports: [
        AttributeLabelComponent,
        CategoryLabelComponent,
        ItemLabelComponent,
        ItemAttributeLabelComponent,
        SaleLabelComponent,
        SubCategoryLabelComponent,
        UserLabelComponent,
        UserAccountLabelComponent
    ]
})
export class EntitySharedModule { }
