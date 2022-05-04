package portaluser.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CloudServiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    private Long id;

    private String name;

//    @ManyToMany
//    @JoinTable(name = "CATEGORY_ITEM",
//            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
//            inverseJoinColumns = @JoinColumn(name = "CLOUD_SERVICE_ID"))
//    private List<CloudService> items = new ArrayList<CloudService>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private CloudServiceCategory parent;

    @OneToMany(mappedBy = "parent")
    private List<CloudServiceCategory> child = new ArrayList<CloudServiceCategory>();

    //==연관관계 메서드==//
    public void addChildCategory(CloudServiceCategory child) {
        this.child.add(child);
        child.setParent(this);
    }

//    public void addItem(CloudService cloudService) {
//        cloudService.add(cloudService);
//    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<CloudService> getItems() {
//        return items;
//    }
//
//    public void setItems(List<CloudService> items) {
//        this.items = items;
//    }

    public CloudServiceCategory getParent() {
        return parent;
    }

    public void setParent(CloudServiceCategory parent) {
        this.parent = parent;
    }

    public List<CloudServiceCategory> getChild() {
        return child;
    }

    public void setChild(List<CloudServiceCategory> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
