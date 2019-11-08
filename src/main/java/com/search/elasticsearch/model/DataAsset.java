package com.search.elasticsearch.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(indexName = "dataasset_index", type = "dataasset")
@Data
public class DataAsset {
	@Id
	private String id;
	private String name;
	private String value;
	private String description;
	private String cluster;
	private String database;
	private String schema_name;
	private List<String> column_names = new ArrayList<>();
	private List<String> tags = new ArrayList<>();
	private Date last_updated_epoch = new Date();

	public DataAsset() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getSchema_name() {
		return schema_name;
	}

	public void setSchema_name(String schema_name) {
		this.schema_name = schema_name;
	}

	public List<String> getColumn_names() {
		return column_names;
	}

	public void setColumn_names(List<String> column_names) {
		this.column_names = column_names;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Date getLast_updated_epoch() {
		return last_updated_epoch;
	}

	public void setLast_updated_epoch(Date last_updated_epoch) {
		this.last_updated_epoch = last_updated_epoch;
	}

}
