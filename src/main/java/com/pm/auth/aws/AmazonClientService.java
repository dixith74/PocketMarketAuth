package com.pm.auth.aws;

import org.springframework.stereotype.Service;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Service
public class AmazonClientService {

	public AmazonS3 getS3Client() {
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new InstanceProfileCredentialsProvider(false)).build();
	}
}
