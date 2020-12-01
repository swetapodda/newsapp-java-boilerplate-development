import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {

  transform(items: any[], searchText: string): any[] {

    if (!items) {
      return [];
    }
    if (!searchText) {
      return items;
    }
    searchText = searchText.toLowerCase();

    // return items.filter(it => {
    //   return it.toLowerCase().includes(searchText);
    // });

    return items.filter(function (el: any) {   // <---- data type!      
      return el.title.toLowerCase().indexOf(searchText) > -1;
    });
  }

}
